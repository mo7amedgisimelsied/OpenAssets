import discordIcon from '../../public/discord-icon.svg';
import '../styles/contribution-styles.css';
function Contribution() {
    return (
        <section className="contribution-section">
            <h2>WANT TO GIVE BACK?</h2>
            <p className='contribution-subtext'>Whether you’re a 3D artist, icon designer, or illustrator, you can help creators everywhere by sharing your work for free. Join our global team of volunteers and make the creative world a little fairer.</p>
            <button className='join-discord'>
                <img src={discordIcon} alt="Discord Icon" />
                Join the Discord →
            </button>

            <div className='site-statistics'>
                <div className='statistics'>
                <p className='number'>128+</p>
                <p className='label'>Designers</p>
                </div>

                <div className='statistics'>
                <p className='number'>4,672+</p>
                <p className='label'>Free Assets</p>
                </div>

                <div className='statistics'>
                <p className='number'>1.2M+</p>
                <p className='label'>Downloads</p>
                </div>

            </div>
        </section>
    );
}

export default Contribution;