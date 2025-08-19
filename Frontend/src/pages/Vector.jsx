import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/vector-styles.css';

function Vector(){

    const vectors_dummy = [
        'https://placehold.co/1920x1080',
        'https://placehold.co/1920x1080',
        'https://placehold.co/1280x720',
        'https://placehold.co/1920x1080',
        'https://placehold.co/1280x720',
        'https://placehold.co/1080x1080',
        'https://placehold.co/1080x1080',
        'https://placehold.co/1080x920',
        'https://placehold.co/1920x1080',
        'https://placehold.co/1080x1920'
    ];
    return (
        <>
            <header className="assets-search-header">
                <Navbar current='Vector' />
                <SearchBar showDropdown={false} />
            </header>
            <main className="vectors-list-container">
                {vectors_dummy.map((url, index) => (
                    <figure key={index}>
                        <a href="">
                            <img src={url} alt={`Vector ${index + 1}`} />
                            <div className="overlay"></div>
                        </a>
                    </figure>
                ))}
            </main>
            <footer>
                <Footer />
            </footer>
        </>
    );
}

export default Vector;