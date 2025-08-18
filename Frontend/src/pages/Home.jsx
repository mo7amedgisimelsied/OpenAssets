import Contribution from "../components/Contibution";
import Hero from "../components/Hero";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import Donation from "../components/Donation";
import '../styles/home-styles.css';

function Home(){
    return (
        <>
        <header className="home-header">
            <Navbar current = "Home"/>
            <Hero />
        </header>
        <main className="home-main">
            <Contribution />
            <Donation />
        </main>
        <footer>
            <Footer />
        </footer>
        </>
    );
}

export default Home;