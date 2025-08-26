import { useState } from "react";
import { useNavigate } from "react-router-dom";
function Navbar(props){
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    let history = useNavigate();
    function navigateTo(path) {
        history(`/${path}`);
        setIsMenuOpen(false);
    }

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (<nav>
        <p>OpenAssets</p>
        <button className="hamburger" onClick={toggleMenu}>
                ☰
            </button>
        <ul className={isMenuOpen ? "nav-links-open" : "nav-links-closed"}>
            <li 
            style={
                props.current === 'Home'? {borderBottom: "solid 1px"}:{border: "none"}
            } 
                onClick={() => navigateTo('home')}>Home</li>

            <li 
            style={
                props.current === 'Icons'? {borderBottom: "solid 1px"}:{border: "none"}
            } 
                onClick={() => navigateTo('icons')}>Icons</li>

            <li 
            style={
                props.current === '3D'? {borderBottom: "solid 1px"}:{border: "none"}
            } 
                onClick={() => navigateTo('3d')}>3D</li>

            <li 
            style={
                props.current === 'Vector'? {borderBottom: "solid 1px"}:{border: "none"}
            } 
                onClick={() => navigateTo('vector')}>Vector</li>
        </ul>
    </nav>);

}
export default Navbar;