import { useState } from "react";

function IconSidebar(props) {
    const { icon } = props;
    const initialSvg = icon.svgCode;
    const sizes = ['50', '100', '150', '250', '500'];
    const colors = [
        '#2BBF5D', '#E74C3C', '#F1C40F', '#9B59B6', '#3498DB', '#1ABC9C', 
        '#2980B9', '#95A5A6', '#27AE60', '#FFC107', '#E67E73', '#8E44AD', 
        '#2C3E50', '#2196F3', '#ECF0F1'
    ];

    const [selectedSize, setSelectedSize] = useState('150');
    const [selectedColor, setSelectedColor] = useState('#2C3E50');


    const getCustomizedSvg = () => {
        let svg = initialSvg.replace(/fill="([^"]*)"/g, `fill="${selectedColor}"`);
        svg = svg.replace(/width="([^"]*)"/, `width="${selectedSize}"`);
        svg = svg.replace(/height="([^"]*)"/, `height="${selectedSize}"`);
        return svg;
    };

    const customizedSvg = getCustomizedSvg();

    const handleDownload = () => {
        const blob = new Blob([customizedSvg], { type: 'image/svg+xml' });
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = `icon-${selectedSize}x${selectedSize}.svg`;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
    };

    const handleCopy = async () => {
        try {
            await navigator.clipboard.writeText(customizedSvg);
            alert('SVG code copied to clipboard!');
        } catch (err) {
            console.error('Failed to copy SVG: ', err);
            alert('Failed to copy SVG code.');
        }
    };

    return (
        <div className="icon-details-sidebar">
            <button className="close-sidebar-button" onClick={() => props.setShowSidebar(false)}>Close</button>
            <div className="icon-details">
                <div className="icon-preview">
                    <div
                        style={{ width: `${selectedSize}px`, height: `${selectedSize}px` }}
                        dangerouslySetInnerHTML={{ __html: customizedSvg }}
                    />
                </div>
                <div className="icon-info">
                    <p>SVG Code</p>
                    <p className="svg-code">
                        {customizedSvg.length > 250 ? `${customizedSvg.substring(0, 250)}...` : customizedSvg}
                    </p>
                    <p>SVG Sizes</p>
                    <div className="sizes-list">
                        {sizes.map(size => (
                            <div key={size}>
                                <input
                                    type="radio"
                                    name="icon-size"
                                    id={`${size}x${size}`}
                                    value={size}
                                    checked={selectedSize === size}
                                    onChange={(e) => setSelectedSize(e.target.value)}
                                />
                                <label htmlFor={`${size}x${size}`}>{size}x{size}</label>
                            </div>
                        ))}
                    </div>
                    <p>Color</p>
                    <div className="fill-colors">
                        {colors.map(color => (
                            <div
                                className={`color ${selectedColor === color ? 'active' : ''}`}
                                style={{ backgroundColor: color }}
                                key={color}
                                onClick={() => setSelectedColor(color)}
                            />
                        ))}
                    </div>
                    <div>
                        <button onClick={handleDownload}
                        style={{width: '100%', backgroundColor: '#2BBF5D', borderRadius: '0'}}>Download</button>

                        <button onClick={handleCopy}
                        style={{width: '100%', color: '#282828', borderRadius: '0'}}>Copy SVG</button>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default IconSidebar;