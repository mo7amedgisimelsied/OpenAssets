import { useState, useEffect } from "react";
import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/vector-styles.css';
import { useSearchParams } from "react-router-dom";

function Vector(){

    const [list, setList] = useState([{id: 1, previewPath: 'https://picsum.photos/1920/1080'}]);
    const [searchParams] = useSearchParams();

    useEffect(() => {
    const searchTerm = searchParams.get('term');
    const fetchVectors = async () => {
        try {
            let url;

            if (searchTerm) {
                url = `http://localhost:8080/vectors/search/term?term=${searchTerm}`;
            } else {
                url = 'http://localhost:8080/vectors';
            }

            const response = await fetch(url);
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            setList(data);
        } catch (error) {
            console.error('Failed to fetch vectors:', error);
            setList([]);
        }
    };
    fetchVectors();
    }, [searchParams]);

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='Vector' />
                <SearchBar showDropdown={false} basePath="/vector"/>
            </header>
            <main className="vectors-list-container">
                {list.map((vector, index) => (
                    <figure key={index}>
                        
                            <img src={vector.previewPath} alt={`Vector ${index + 1}`} />
                            <div className="overlay">
                                <a href={`http://localhost:8080/vectors/download/${vector.id}`}>Download</a>
                            </div>
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