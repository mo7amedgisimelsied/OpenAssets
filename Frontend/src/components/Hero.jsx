import searchicon from '../../public/search-icon.svg';
function Hero(){
    return (
        <>
            <h1>Creativity shouldn’t come with a price tag</h1>
            <h2>No paywalls. No tricks. Just free assets from designers who believe creativity should be open to everyone.</h2>
            <div>
                <img src={searchicon} alt="Icon" />
                <input type="text" placeholder="Search for assets..." />
                <select name="asset-type" id="">
                    <option value="icon">Icons</option>
                    <option value="3d">3D</option>
                    <option value="vector">Vector</option>
                </select>
            </div>

            <div>
                <p><span>✔</span> 100% free downloads</p>
                <p><span>✔</span> No account required</p>
                <p><span>✔</span> Community-contributed content</p>
            </div>
        </>
    );
}

export default Hero;