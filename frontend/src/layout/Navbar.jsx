import { FaBoxOpen } from "react-icons/fa";
import { RiBearSmileFill, RiBearSmileLine } from "react-icons/ri";
import { TbBasketHeart } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

export default function Navbar({username}){
    return(
        <div className="bg-[#ffa1b7] px-20 h-[5rem] absolute inset-x-0 top-0 flex items-center justify-between">
            <a href="/home" className="h-full"><div className= " bg-[url(../../logo-name.svg)] w-45 h-full bg-no-repeat bg-left bg-contain"></div></a>
            <div class="flex gap-5 justify-end">
                <a href="/cart" className="h-full"><div className="text-3xl"><TbBasketHeart className="text-amber-50 hover:text-[#885d3f]"/></div></a>
                <a href="/order" className="h-full"><div className="text-3xl"><FaBoxOpen className="text-amber-50 hover:text-[#885d3f]"/></div></a>
                <div className="text-3xl"><RiBearSmileLine className="text-amber-50 hover:text-[#885d3f]"/></div>
            </div>
        </div>
    );
}