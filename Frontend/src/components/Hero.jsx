import searchicon from '../../public/search-icon.svg';
function Hero(){
    return (
        <>
            <h1 className='hero-title'>Creativity shouldn’t come with a price tag</h1>
            <p className='hero-title-subtext'>No paywalls. No tricks. Just free assets from designers who believe creativity should be open to everyone.</p>
            <div className='hero-search'>
                <img src={searchicon} alt="Icon" />
                <input type="text" placeholder="Search" />
                <select name="asset-type" id="">
                    <option value="icon">Icons</option>
                    <option value="3d">3D</option>
                    <option value="vector">Vector</option>
                </select>
            </div>

            <div className='hero-features'>
                <p><span>✔</span> 100% free downloads</p>
                <p><span>✔</span> No account required</p>
                <p><span>✔</span> Community-contributed content</p>
            </div>
        </>
    );
}

export default Hero;