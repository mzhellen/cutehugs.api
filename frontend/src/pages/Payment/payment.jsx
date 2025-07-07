import { CardPayment, initMercadoPago } from "@mercadopago/sdk-react";
import { Bounce, ToastContainer, toast } from 'react-toastify';
import { useEffect } from "react";
import api from "../../controllers/api";
import { useNavigate } from "react-router-dom";

export default function Payment(){

    const totalPrice = localStorage.getItem("totalPrice")
    const order_id = localStorage.getItem("orderId");
    const navigate = useNavigate();

    useEffect(() => {
        initMercadoPago('TEST-3828f7de-a7d3-4fdb-97b1-f6015e77692f', {locale: 'pt-BR'});
    },[]);

    //função pra atualizar o order
    function updateOrder(){
        api.patch(`/api/order/${order_id}?orderStatus=PAYED`)
        .then(function(response){
            console.log('deu certo atualizar o order', response);
            navigate('/order');  // adicione alguma coisa pra esperar o alerta de sucesso
        }).catch(function(error){
            console.error("deu errado atualizar o order", error);
        })
    }


    // criação de pagamento 
    function createPayment(paymentMethod){
        api.post("/api/payment",{order_id: order_id, amount: totalPrice, paymentMethod: paymentMethod})
        .then(function(response){
            console.log('deu certo o pagamento', response)
            toast.success('Pagamento autorizado!', {
                    position: "top-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: false,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light",
                    transition: Bounce,
                });
            updateOrder();
        }).catch(function(error){
            console.error("deu errado o pagamento", error);
        })
    }

    function paymentMP(cardFormData){
        api.post("api/payment/pay", {
            token: cardFormData.token,
            amount: cardFormData.transaction_amount,
            description: "test payment",
            installments: cardFormData.installments,
            paymentMethodId: cardFormData.payment_method_id,
            issuerId: cardFormData.issuer_id,
            email: cardFormData.payer?.email,
            cpf: cardFormData.payer?.identification?.number
        }).then(function(response){

            console.log(response.data);

            if (response.data.status == "approved") {
                const methodMP = (response.data.payment_type_id === "credit_card" ? "CREDIT" : "DEBIT") + '_CARD';
                createPayment(methodMP);
                console.log("deu certo o pagamento(mercado pago)"), response.data.status;
            }else{
                toast.error('Pagamento não autorizado. Tente novamente!', {
                    position: "top-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: false,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light",
                    transition: Bounce,
                });
            } 
        }).catch(function(error){
            console.error("deu errado o pagamento(mercado pago)", error);
        })
    }

    return(
        <div className="bg-[#fde8eb] p-24 h-screen">
            <ToastContainer/>
            <CardPayment initialization={{amount: totalPrice}} onSubmit={async (cardFormData) => { paymentMP(cardFormData) }}/>
        </div>
    );
}
