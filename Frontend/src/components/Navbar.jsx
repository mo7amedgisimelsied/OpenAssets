function Navbar(props){

    return (<nav>
        <p>OpenAssets</p>
        <ul>
            <li style={props.current === 'Home'? {borderBottom: "solid 1px"}:{border: "none"}}>Home</li>
            <li style={props.current === 'Icons'? {borderBottom: "solid 1px"}:{border: "none"}}>Icons</li>
            <li style={props.current === '3D'? {borderBottom: "solid 1px"}:{border: "none"}}>3D</li>
            <li style={props.current === 'Vector'? {borderBottom: "solid 1px"}:{border: "none"}}>Vector</li>
        </ul>
    </nav>);

}
export default Navbar;