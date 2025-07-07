import { useState } from "react";
import api from "../controllers/api"
import { TbShoppingBagMinus, TbShoppingBagPlus } from "react-icons/tb";

export default function CartItem({cartItem}){

    // update cartItem
    function update(valor){
        api.patch('/api/cart', {id: `${cartItem.id}`, quantity: valor})
        .then(function (response) {
            console.log('deu certo o update', response);
            window.location.reload();
        }).catch(function (error) {
            console.log('deu erro no update', error);
        })
    }

    const handleIncrement = () => {
        update(1);
    };

    const handleDecrement = () => {
        update(0);
    };

    return(

        <div className="bg-[#fde8eb] rounded-4xl h-60 flex items-center m-8 w-300">
            <div className="flex justify-between m-10 items-center">
                <div className="grid grid-cols-3 items-center justify-center w-90 h-100 rounded-4xl text-[#885d3f] gap-5">
                    <div className="bg-amber-50/50 w-40 h-40 rounded-4xl flex items-center justify-center">
                        <img className="w-full h-full rounded-4xl" src={`${cartItem.product.imageURL}`} alt="imagem" />
                    </div>
                    <div className="flex flex-col md:flex-row items-center">
                        <div className="flex flex-col w-100 mx-25 gap-2">
                            <div className="font-bold w-100">
                                <h2>{cartItem.product.name}</h2>
                            </div>
                            <div>
                                <p>Descrição: {cartItem.product.description}</p>
                                <p>Subtotal: R${cartItem.subTotal}</p>
                                <p>Quantidade: {cartItem.quantity}</p>
                            </div>
                        </div>
                    </div>
                    <div className="mx-180">
                        <div className="text-3xl m-3 "><TbShoppingBagPlus onClick={handleIncrement} className="hover:text-[#ffa1b7]"/></div>
                        <div className="text-3xl m-3"><TbShoppingBagMinus onClick={handleDecrement} className="hover:text-[#ffa1b7]" /></div>
                    </div>
                </div>
            </div>
        </div>

    )
}