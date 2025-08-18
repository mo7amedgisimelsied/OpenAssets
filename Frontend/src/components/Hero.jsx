import abstractShape from '../../public/abstract-shape.svg';
import shape from '../../public/shape.svg';
import SearchBar from './SearchBar';
function Hero(){


    return (
        <>
            <img src={abstractShape} alt="background" className='abstract-shape'/>
            <img src={shape} alt="shape" className='shape'/>
            <h1 className='hero-title'>Creativity shouldn’t come with a price tag</h1>
            <p className='hero-title-subtext'>No paywalls. No tricks. Just free assets from designers who believe creativity should be open to everyone.</p>
            <SearchBar showDropdown = {true}/>

            <div className='hero-features'>
                <p><span>✔</span> 100% free downloads</p>
                <p><span>✔</span> No account required</p>
                <p><span>✔</span> Community-contributed content</p>
            </div>
        </>
    );
}

export default Hero;