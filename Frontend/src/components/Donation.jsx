import '../styles/donation-styles.css';
import profile1 from '../../public/profile1.jpg';
import profile2 from '../../public/profile2.jpg';
import profile3 from '../../public/profile3.jpg';
import profile4 from '../../public/profile4.jpg';
function Donation() {
    return (
        <section className="donation-section">
            <h2>Keep creativity free for everyone</h2>
            <p className='donation-subtext'>We don't run ads, but servers and storage still cost money. Your donation helps us keep providing free, high-quality assets to anyone who needs them.</p>

            <p className='top-supporters'>Top Supporters</p>
            <div className='supporters-pics'>
                <img src={profile1} alt="supporter" />
                <img src={profile2} alt="supporter" />
                <img src={profile3} alt="supporter" />
                <img src={profile4} alt="supporter" />
            </div>
            <button className='donate-button'>Donate Now</button>
        </section>
    );
}

export default Donation;