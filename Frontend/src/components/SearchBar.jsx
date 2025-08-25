import searchicon from '../../public/search-icon.svg';
function SearchBar({showDropdown, setAssetList, url}) {


    function handleSearch(){
        fetch(url + 'term?term=' + document.querySelector('input').value).then(res => res.json()).then(data => {setAssetList(data)})
    }
    document.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    });

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };

    let assetType;
    return (
        <div className='search-bar'>
            <img src={searchicon} alt="search icon" onClick={handleSearch}/>
            <input
            type="text"
            placeholder='Search'
            onKeyDown={handleKeyDown}/>
            {showDropdown && (
                <select
                    name="asset-type"
                    value={assetType}
                    defaultValue={'icon'}
                    onChange={e => assetType = e.target.value}
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