import linkedinIcon from '../../public/linkedin-icon.svg';
import discordIcon from '../../public/discord-icon.svg';
import youtubeIcon from '../../public/youtube-icon.svg';
import xIcon from '../../public/x-icon.svg';
function Footer(){
    return (
        <>
            <div className="footer-links">
                <img src={linkedinIcon} alt="linkedin" />
                <img src={discordIcon} alt="discord" />
                <img src={youtubeIcon} alt="youtube" />
                <img src={xIcon} alt="x" />
            </div>
            <p>© 2025 OpenAssets. All rights reserved.</p>
        </>
    );
}

export default Footer;