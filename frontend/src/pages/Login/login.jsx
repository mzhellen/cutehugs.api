import React, { useState } from 'react';
import api from '../../controllers/api'
import { useNavigate } from 'react-router-dom';

export default function Login(){

  // cria um espaço para as informações do usuário que inicia vazia
    const [form, setForm] = useState({ 
      email: "",
      password: ""
    }); 

    const navigate = useNavigate();

    // capta o valor onde o evento é disparado e atualiza o form
    function haldleChange(e){
      const { name, value } = e.target;
      setForm((prev) => ({ ...prev, [name]: value}));
    }   

    // função para criação de usuário em comunicação com o backend 
    function login(event) {
        event.preventDefault();
      api.post('/api/users/auth', form)
      .then( function (response ) {
        console.log('Login realizado com sucesso!', response.data)
        if(response.data.token){
          localStorage.setItem("token", response.data.token)
          console.log("deu certo no token")
          navigate("/home")
        }else{
          console.log("deu erro no token")
        }
      })
      .catch(function (error) {
        console.error('Erro ao tentar fazer login: ', error)
      })
    }

    return (
      <div className="w-screen h-screen bg-[url(../../bg.png)] bg-cover flex items-center justify-center">
        <div className="flex flex-col items-center justify-center bg-pink-200 rounded-lg p-3 border-5 border-pink-100">
        <form>
          <div className="text-center p-4 text-orange-950"> 
            <h1>Login</h1>
          </div>
          <div className='p-2 flex flex-col  text-orange-950' >
            <label>
              Email:
            </label>
            <input 
              name="email" 
              type="email" 
              value={form.email}
              onChange={haldleChange} 
              className='bg-pink-100 rounded-lg h-8 p-2'
            />
          </div>
          <div className='p-2 flex flex-col  text-orange-950' >
            <label>
              Senha:
            </label>
            <input
              name="password" 
              type="password" 
              value={form.password}
              onChange={haldleChange} 
              className='bg-pink-100 rounded-lg h-8 p-2'
            />
          </div>

          <div className='text-center bg-pink-300 p-3  text-orange-950 hover:bg-pink-400 rounded-lg'>
            <button className='h-full' type="button" onClick={login}>Entrar</button>
          </div>

        </form>
      </div>
    </div>  
    );
}