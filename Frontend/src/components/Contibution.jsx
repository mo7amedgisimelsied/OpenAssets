import discordIcon from '../../public/discord-icon.svg';
import star from '../../public/star.svg';
import starOutlined from '../../public/star-outlined.svg';
import '../styles/contribution-styles.css';
import { useNavigate } from "react-router-dom";
function Contribution() {
    let history = useNavigate();
    return (
        <section className="contribution-section">
            <img src={star} alt="star-background" className='star' />
            <img src={starOutlined} alt="star-outlined-background" className='star-outlined' />
            <h2>WANT TO GIVE BACK?</h2>
            <p className='contribution-subtext'>Whether you’re a 3D artist, icon designer, or illustrator, you can help creators everywhere by sharing your work for free. Join our global team of volunteers and make the creative world a little fairer.</p>
            <div style={{display: "flex", gap: "1rem", marginTop: '3rem', flexWrap: "wrap"}}>
                <button className='upload-assets' onClick={() => history('/upload') }>Upload Assets</button>
                <button className='join-discord'>
                    <img src={discordIcon} alt="Discord Icon" />
                    Join the Discord →
                </button>
            </div>

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