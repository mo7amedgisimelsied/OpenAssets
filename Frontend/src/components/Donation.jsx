import '../styles/donation-styles.css';
function Donation() {
    return (
        <section className="donation-section">
            <h2>Keep creativity free for everyone</h2>
            <p className='donation-subtext'>We run on love, not ads, but servers and storage still cost money. Your donation helps us keep providing free, high-quality assets to anyone who needs them.</p>

            <p className='top-supporters'>Top Supporters</p>
            <div className='supporters-pics'>
                <img src="https://placehold.co/100x100" alt="supporter" />
                <img src="https://placehold.co/200x200" alt="supporter" />
                <img src="https://placehold.co/200x200" alt="supporter" />
                <img src="https://placehold.co/200x200" alt="supporter" />
            </div>
            <button className='donate-button'>Donate Now</button>
        </section>
    );
}

export default Donation;