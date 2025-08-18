import searchicon from '../../public/search-icon.svg';
function SearchBar({showDropdown}) {
    let assetType;
    return (
        <div className='search-bar'>
            <img src={searchicon} alt="search icon" />
            <input type="text" placeholder='Search' />
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