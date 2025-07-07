import React, { useState } from 'react';
import api from '../../controllers/api'
import { useNavigate } from 'react-router-dom';

export default function Register(){

  // cria um espaço para as informações do usuário que inicia vazia
    const [form, setForm] = useState({ 
      name: '',
      email: '',
      password: '',
      admin: false
    }); 

    const navigate = useNavigate();

    // capta o valor onde o evento é disparado e atualiza o form
    function haldleChange(e){
      const { name, value } = e.target;
      setForm((prev) => ({ ...prev, [name]: value}));
    }   

    // função para criação de usuário em comunicação com o backend 
    function createUser(event) {
        event.preventDefault();
      api.post('/api/users/register', form)
      .then( function (response ) {
        console.log('Usuário cadastrado com sucesso!', response.data)
        navigate("/")
      })
      .catch(function (error) {
        console.error('Erro ao cadastrar: ', error)
      })
    }

    return (
      <div className="min-h-screen bg-[url(../../bg.png)] bg-cover flex items-center justify-center">
        <div className="flex flex-col items-center justify-center bg-pink-200 rounded-lg p-3 border-5 border-pink-100 ">
        <form>
          <div className="text-center p-4 text-orange-950"> 
            <h1>Criar Conta</h1>
          </div>

          <div className='p-2 flex flex-col  text-orange-950'>
            <label>
              Nome:
            </label>
            <input 
              name="name" 
              type="text"
              value={form.name}
              onChange={haldleChange} 
              className='bg-pink-100 rounded-lg h-8 p-2'
            />
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
            <button type="button" onClick={createUser}>Cadastrar</button>
          </div>

        </form>
      </div>
    </div>  
    );
}