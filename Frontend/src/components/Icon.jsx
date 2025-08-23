function Icon(props) {
    // this component should recieve an SVG code as a prop
    return (
        <div onClick={() => {props.setShowSidebar((prev) => {return !prev}); console.log('clicked'); props.setSelectedIcon(props.svgCode)}}
            className="icon-container"
            dangerouslySetInnerHTML={{ __html: props.svgCode }}
        />
    );
}

export default Icon;