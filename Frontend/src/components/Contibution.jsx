import discordIcon from '../../public/discord-icon.svg';
function Contribution() {
    return (
        <section>
            <h2>WANT TO GIVE BACK?</h2>
            <p>Whether you’re a 3D artist, icon designer, or illustrator, you can help creators everywhere by sharing your work for free. Join our global team of volunteers and make the creative world a little fairer.</p>
            <button>
                <img src={discordIcon} alt="Discord Icon" />
                Join the Discord
            </button>

            <div>
                <div>
                <p>128+</p>
                <p>Designers</p>
                </div>

                <div>
                <p>4,672+</p>
                <p>Free Assets</p>
                </div>

                <div>
                <p>1.2M+</p>
                <p>Downloads</p>
                </div>

            </div>
        </section>
    );
}

export default Contribution;