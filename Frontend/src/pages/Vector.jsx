import { useState, useEffect } from "react";
import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/vector-styles.css';
import { useSearchParams } from "react-router-dom";
import AssetNotFound from "../components/AssetNotFound";

function Vector(){

    const [vectorsList, setVectorsList] = useState([]);
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
            setVectorsList(data);
        } catch (error) {
            console.error('Failed to fetch vectors:', error);
            setVectorsList([]);
        }
    };
    fetchVectors();
    }, [searchParams]);

    function FetchedVectors(list){
        return list.map((vector, index) => (
                    <figure key={index}>
                            <img src={vector.previewPath} alt={`Vector ${index + 1}`} />
                            <div className="overlay">
                                <a href={`http://localhost:8080/vectors/download/${vector.id}`}>Download</a>
                            </div>
                    </figure>
                ));
    }

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='Vector' />
                <SearchBar showDropdown={false} basePath="/vector"/>
            </header>
            <main className="vectors-list-container">
                {vectorsList.length > 0 ? FetchedVectors(vectorsList) : <AssetNotFound />}
            </main>
            <footer>
                <Footer />
            </footer>
        </>
    );
}

export default Vector;