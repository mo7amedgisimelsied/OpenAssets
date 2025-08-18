function Icon(props) {
    // this component should recieve an SVG code as a prop
    return (
        <div
            className="icon-container"
            dangerouslySetInnerHTML={{ __html: props.svgCode }}
        />
    );
}

export default Icon;