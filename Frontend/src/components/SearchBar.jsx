import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import searchicon from '../../public/search-icon.svg';

function SearchBar({ showDropdown, basePath }) {
    const navigate = useNavigate();
    const [assetType, setAssetType] = useState('icon');
    const [searchTerm, setSearchTerm] = useState('');

    function handleSearch() {
        let destinationPath;
        if (showDropdown) {
            destinationPath = assetType === 'icon' ? '/icons' : `/${assetType}`;
        } else {
            destinationPath = basePath;
        }
        navigate(`${destinationPath}?term=${searchTerm}`);
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };

    function handleTypeChange(e) {
        setAssetType(e.target.value);
    }
    
    return (
        <div className='search-bar'>
            <img src={searchicon} alt="search icon" onClick={handleSearch} />
            <input
                type="text"
                placeholder='Search'
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                onKeyDown={handleKeyDown}
            />
            {showDropdown && (
                <select
                    name="asset-type"
                    value={assetType}
                    onChange={handleTypeChange}
                >
                    <option value="icon">Icons</option>
                    <option value="3d">3D</option>
                    <option value="vector">Vector</option>
                </select>
            )}
        </div>
    );
}

export default SearchBar;