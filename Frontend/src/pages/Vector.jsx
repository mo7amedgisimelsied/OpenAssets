import { useState, useEffect } from "react";
import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import SearchBar from "../components/SearchBar";
import '../styles/search-bar-styles.css';
import '../styles/vector-styles.css';

function Vector(){

    const [list, setList] = useState([]);

    useEffect(() => {
        // fetch icons from API
        fetch('http://localhost:8080/vectors')
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setList(data);
      });

    }, []);

    function handleDownload(id){
        fetch(`http://localhost:8080/vectors/download/${id}`, {
            headers: {
                'Content-Type': 'application/octet-stream'
            }
        })
        .then((res) => res.blob())
        .then((blob) => {
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `vector-${id}`);
            link.click();
        });
    }

    return (
        <>
            <header className="assets-search-header">
                <Navbar current='Vector' />
                <SearchBar showDropdown={false} />
            </header>
            <main className="vectors-list-container">
                {list.map((vector, index) => (
                    <figure key={index}>
                            <img src={vector.previewPath} alt={`Vector ${index + 1}`} />
                            <div className="overlay">
                                <button onClick={() => handleDownload(vector.id)}>Download</button>
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