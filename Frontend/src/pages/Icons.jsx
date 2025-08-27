import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import '../styles/icons-styles.css';
import SearchBar from "../components/SearchBar";
import { useEffect, useState } from "react";
import IconSidebar from "../components/IconSidebar";
import { useSearchParams } from "react-router-dom";
function Icons(){
    const [iconsList, setIConsList] = useState([]);
    const [showSidebar, setShowSidebar] = useState(false);
    const [selectedIcon, setSelectedIcon] = useState(null);
    const [searchParams] = useSearchParams();
    
    useEffect(() => {
    const searchTerm = searchParams.get('term');
    const fetchIcons = async () => {
        try {
            let url;

            if (searchTerm) {
                url = `http://localhost:8080/icons/search/term?term=${searchTerm}`;
            } else {
                url = 'http://localhost:8080/icons';
            }

            const response = await fetch(url);
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            setIConsList(data);
        } catch (error) {
            console.error('Failed to fetch icons:', error);
            setIConsList([]);
        }
    };
    fetchIcons();
    }, [searchParams]);

    return (
        <>
        <header className="assets-search-header">
            <Navbar current="Icons" />
                <SearchBar showDropdown={false} basePath="/icons"/>
        </header>
        <main className="icons-list-container">
                    {iconsList.map((icon, index) => (
                        <div key={index}
                        onClick={() => {setSelectedIcon(icon); setShowSidebar((prev) => {return !prev})}}
                        className="icon-container"
                        dangerouslySetInnerHTML={{ __html: icon.svgCode }}
        />
                    ))}
                    {showSidebar && <IconSidebar icon = {selectedIcon} setShowSidebar={setShowSidebar}/>}
        </main>
        <footer>
            <Footer />
        </footer>
        </>
    );
}

export default Icons;