import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/models-styles.css';
import { useEffect, useState } from "react";

function Models(){
    const [modelsList, setModelsList] = useState([]);

    useEffect(() => {
            // fetch icons from API
            fetch('http://localhost:8080/models')
          .then((res) => {
            return res.json();
          })
          .then((data) => {
            setModelsList(data);
          });
    
        }, []);

        function handleDownload(id){
        fetch(`http://localhost:8080/models/download/${id}`, {
            headers: {
                'Content-Type': 'application/octet-stream'
            }
        })
        .then((res) => res.blob())
        .then((blob) => {
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `model-${id}`);
            link.click();
        });
    }

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='3D' />
                <SearchBar showDropdown={false} url = {'http://localhost:8080/models/search/'} setAssetList={setModelsList}/>
            </header>
            <main className="models-list-container">
                    {modelsList.map((model, index) => (
                    <figure key={index}>
                            <img src={model.previewPath} alt={`model ${index + 1}`} />
                            <div className="overlay">
                                <button onClick={() => handleDownload(model.id)}>Download</button>
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

export default Models;