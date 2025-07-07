import { useEffect, useState } from "react";
import Produto from "../../components/Produto";
import api from "../../controllers/api";

export default function Home(){
    const [products, setProducts] = useState([]);

    useEffect(() => {
    const getProducts = async () => {
      try {
        const response = await api.get('/api/products'); 
        const data = response.data; 
        setProducts(data); 
        console.log('achei os produtos', data);
      } catch (error) {
        console.error('não peguei os produtos', error);
      }
    };
    
    getProducts();
  }, [setProducts]);

    return(
        <div className="bg-[#fde8eb] p-24">
            <div className="grid grid-cols-4 4xl:grid-cols-4 bg-[#fde8eb]">
                {
                products.map(product => (
                    <Produto key={product.id} produto={product}/>
                ))}
            </div>
          </div>
    );


};