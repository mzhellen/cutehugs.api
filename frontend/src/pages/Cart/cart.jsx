import { useContext, useEffect, useState } from "react";
import { AppContext } from "../../layout/Template";
import CartItem from "../../components/CartItem";
import api from "../../controllers/api";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";

export default function Cart(){

    const token = localStorage.getItem("token");
    const {listCartItems} = useContext(AppContext);
    console.log(listCartItems);

    const [total, setTotal] = useState(0);

    const navigate = useNavigate();

    // função pra rodar a lista de items e ir somando o subtotal
    useEffect(() => {
        const total = listCartItems.reduce((accumulator, cartItem) => {
            return accumulator + cartItem.subTotal;
        }, 0);
        setTotal(total);
    }, [listCartItems]);

    function createOrder(){
        api.post('/api/order', {user_id: jwtDecode(token).id, totalPrice: total})
        .then(function (response) {
            console.log("pedido criado", response.data);
            localStorage.setItem("orderId", response.data.id);
            navigate('/order');
        }).catch( function (error) {
            console.log("não deu certo o pedido", error);
        })
    }

    return(
        <div className="bg-[#fde8eb] p-24 h-screen">
            <div className="bg-[#885d3f]/50 rounded-4xl flex flex-col-2 justify-between">
                <div className="mx-10">
                    {
                        listCartItems.map (cartItem => (
                            <CartItem key={cartItem.id} cartItem={cartItem}/>
                        ))
                    }
                </div>
                <div className="flex items-center h-30 bg-[#fde8eb] w-90 rounded-3xl mr-10 mt-10 mb-8">
                    <div className="flex items-center">
                        <button onClick={createOrder} className="bg-[#885d3f] text-[#fde8eb] flex items-center justify-center mx-15 w-60 rounded-4xl p-3 hover:text-[#885d3f] hover:bg-[#fde8eb] border-2 border-[#885d3f]">
                            Finalizar compra
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}