import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import '../styles/icons-styles.css';
import SearchBar from "../components/SearchBar";
import { useEffect, useState } from "react";
import IconSidebar from "../components/IconSidebar";
function Icons(){
    const [iconsList, setIConsList] = useState([]);
    const [showSidebar, setShowSidebar] = useState(false);
    const [selectedIcon, setSelectedIcon] = useState(null);
    useEffect(() => {
        // fetch icons from API
        fetch('http://localhost:8080/icons')
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setIConsList(data);
      });

    }, []);
    return (
        <>
        <header className="assets-search-header">
            <Navbar current="Icons" />
                <SearchBar showDropdown={false} url = {'http://localhost:8080/icons/search/'} setAssetList={setIConsList}/>
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