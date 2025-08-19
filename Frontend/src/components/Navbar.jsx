import { useNavigate } from "react-router-dom";
function Navbar(props){
    let history = useNavigate();
    function navigateTo(path) {
        history(`/${path}`);
    }
    return (<nav>
        <p>OpenAssets</p>
        <ul>
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