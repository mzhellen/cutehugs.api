import { jwtDecode } from "jwt-decode";
import api from "../../controllers/api";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function Order(){

    const token = localStorage.getItem("token");
    const order_id = localStorage.getItem("orderId");
    const [order, setOrder] = useState('');
    const navigate = useNavigate();

    useEffect(() => {

        const Order = async () => {
            try{
                const response = await api.get(`/api/order/show/${order_id}`);
                const data = response.data; 
                console.log('deu certo o order', data);

                // filtragem de dados
                if( data.user_id === jwtDecode(token).id){
                    setOrder(data);
                    localStorage.setItem("totalPrice", data.totalPrice);  
                }
            
            } catch (error) {
                 console.error('deu errado o order:', error);
            }
        };

        Order();
    }, [setOrder]);

    console.log("vixe",order);

    const goPay = () => {
      navigate('/payment');
    }

    const desabilitar = order.orderStatus === 'PAYED'

    return(
            <div className="bg-[#fde8eb] p-24 h-screen">
                <div className="bg-[#885d3f]/50 rounded-4xl flex flex-col-2 justify-between h-70">
                    <div className="mx-10 lex items-center h-50 bg-[#fde8eb] w-300 rounded-3xl mr-10 mt-10 ">
                        <div>
                            <div className="font-bold w-50 text-[#885d3f] ml-10 mt-15">
                                <p>Id: {order.id}</p>
                                <h2>Status: {order.orderStatus}</h2>
                                <p>Valor total: R${order.totalPrice}</p>
                            </div>
                        </div>
                    </div>
                    <div className="flex items-center h-50 bg-[#fde8eb] w-90 rounded-3xl mr-10 mt-10">
                        <div className="flex items-center">
                            <button onClick={goPay} disabled={desabilitar} className="bg-[#885d3f] text-[#fde8eb] flex items-center justify-center mx-15 w-60 rounded-4xl p-3 hover:text-[#885d3f] hover:bg-[#fde8eb] border-2 border-[#885d3f]">
                                Realizar pagamento
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
}