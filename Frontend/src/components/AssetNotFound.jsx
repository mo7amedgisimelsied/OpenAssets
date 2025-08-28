import sadFace from '../../public/Sad-face.svg'

function AssetNotFound() {
    return (
        <div 
        style={
            {display: "flex",
            flexDirection: "column",
            alignItems: "center",
            width: "100%",
        gridColumn: "1 / -1"}
            }>
            <img src={sadFace} alt="sad face"
            style={
                {width: "15rem"}
                }/>
            <p
            style={
                {textAlign: "center",
                color: "#777777ff",
                fontSize: "1.2rem",
                marginTop: "1rem"}
                }>Sorry, we couldn't find any results</p>
        </div>
    );
}

export default AssetNotFound;