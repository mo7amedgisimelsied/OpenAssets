import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import '../styles/icons-styles.css';
import SearchBar from "../components/SearchBar";
import Icon from "../components/Icon";
import linkedInIcon from '../../public/linkedin-icon.svg?raw';
function Icons(){
    let iconsList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    return (
        <>
        <header className="icons-header">
            <Navbar current="Icons" />
            
            <div className="search-container">
                <SearchBar showDropdown={false} />

                <div className="search-filters">
                <div className="filter-label">
                    <input type="radio" name="icon-style" id="fill" />
                    <span className="checkmark"></span>
                    <label htmlFor="fill" style={{ marginLeft: '0.6rem' }}>Fill</label>
                </div>
                <div className="filter-label">
                    <input type="radio" name="icon-style" id="outline" />
                    <span className="checkmark"></span>
                    <label htmlFor="outline" style={{ marginLeft: '0.6rem' }}>Outline</label>
                </div>
                </div>
            </div>
        </header>
        <main className="icons-list-container">
                    {iconsList.map((icon, index) => (
                        <Icon key={index} svgCode = {linkedInIcon} />
                    ))}
        </main>
        <footer>
            <Footer />
        </footer>
        </>
    );
}

export default Icons;