import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/models-styles.css';

function Models(){
    const models_dummy = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='3D' />
                <SearchBar showDropdown={false} />
            </header>
            <main className="models-list-container">
                    {models_dummy.map((index) => (
                        <div className="model" key={index}>
                            
                        </div>
                    ))}
            </main>
            <footer>
                <Footer />
            </footer>
        </>
    );
}

export default Models;