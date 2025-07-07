import Template from "../layout/Template";
import Cart from "./Cart/cart";
import Home from "./Home/home";
import Login from "./Login/login";
import Order from "./Order/order";
import Payment from "./Payment/payment";
import Register from "./Register/register";

const Pages = [
    {
        path:"/register",
        component:<Register/>
    },

    {
        path:"/",
        component:<Login/>
    },

    {
        path:"/home",
        component:<Template><Home/></Template>
    },

    {
        path:"/cart",
        component:<Template><Cart/></Template>
    },

    {
        path:"/order",
        component:<Template><Order/></Template>
    },

    {
        path:"/payment",
        component:<Template><Payment/></Template>
    }
]

export default Pages;