import { MdOutlineAddShoppingCart } from "react-icons/md";
import api from "../controllers/api";
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function Produto({produto}){

    const token = localStorage.getItem("token");
    const [item, setItem] = useState({
        quantity: 1,
        cart_id: jwtDecode(token).id,
        product_id: `${produto.id}`
    });
    

    async function addCartItem(){
        await api.post('/api/cart/insertItem', item)
        .then( function (response ) {
            console.log('Produto adicionado ao carrinho com sucesso!', response.data)
        })
        .catch(function (error) {
            console.error('Erro ao adicionar produto: ', error)
        })
    }

    return(
        <div className="bg-[#fde8eb] flex items-center">
            <div className="flex flex-col items-center justify-center w-90 h-110 bg-[#885d3f]/50 m-10 rounded-4xl text-amber-50 gap-5">
                <div className="bg-amber-50/50 w-80 h-85 rounded-4xl flex items-center justify-center">
                    <img className="w-full h-full rounded-4xl" src={`${produto.imageURL}`} alt="imagem" />
                </div>
                <div className="flex flex-col md:flex-row items-center">
                    <div className="flex flex-col w-50 mx-8">
                    <div className="font-bold w-50">
                        <h2>{produto.name}</h2>
                    </div>
                        <div>
                            <p>R${produto.price}</p>
                        </div>
                    </div>
                    <div className="grid justify-items-end text-2xl mt-5 hover:text-[#885d3f]" onClick={addCartItem}><MdOutlineAddShoppingCart /></div>
                </div>
            </div>
        </div>
    );
}