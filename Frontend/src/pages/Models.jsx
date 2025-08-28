import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/models-styles.css';
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import AssetNotFound from "../components/AssetNotFound";

function Models(){
    const [modelsList, setModelsList] = useState([]);
    const [searchParams] = useSearchParams();

    useEffect(() => {
    const searchTerm = searchParams.get('term');
    const fetchModels = async () => {
        try {
            let url;

            if (searchTerm) {
                url = `http://localhost:8080/models/search/term?term=${searchTerm}`;
            } else {
                url = 'http://localhost:8080/models';
            }

            const response = await fetch(url);
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            setModelsList(data);
        } catch (error) {
            console.error('Failed to fetch models:', error);
            setModelsList([]);
        }
    };
    fetchModels();
    }, [searchParams]);

    function FetchedModels(list){
        return list.map((model, index) => (
                        <figure key={index}>
                                <img src={model.previewPath} alt={`model ${index + 1}`} />
                                <div className="overlay">
                                    <a href={`http://localhost:8080/models/download/${model.id}`}>Download</a>
                                </div>
                        </figure>
                ));
    }

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='3D' />
                <SearchBar showDropdown={false} basePath="/3d"/>
            </header>
            <main className="models-list-container">
                    {modelsList.length > 0 ? FetchedModels(modelsList) : <AssetNotFound />}
            </main>
            <footer>
                <Footer />
            </footer>
        </>
    );
}

export default Models;