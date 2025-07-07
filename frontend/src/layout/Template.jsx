import { jwtDecode } from "jwt-decode";
import { createContext, useEffect, useState } from "react";
import api from "../controllers/api";
import Navbar from "./Navbar";

export const AppContext = createContext();

export default function Template({children}){
    const token = localStorage.getItem("token");
    const [username, setUsername] = useState("Usuário");
    const [listCartItems, setlistCartItems] = useState([]);
 
    useEffect(() => {
        if(token){
            const decodeToken = jwtDecode(token)
            setUsername(decodeToken.name)
        }  
    }, [token])

    useEffect(() => {
        const getListCartItem = async () => {
            try {
                const decodeToken = jwtDecode(token)
                const response = await api.get(`api/cart/list/${decodeToken.id}`)
                const value = response.data
                setlistCartItems(value)
                
            }catch (error){
                console.log(error)
            }
        }
        getListCartItem();
    }, [setlistCartItems])

    return(
        <AppContext.Provider value={{listCartItems, setlistCartItems}}>
            <Navbar username={username}/>
            {children}
        </AppContext.Provider>
    );
}
