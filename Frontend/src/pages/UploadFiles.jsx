import { useState } from 'react';
import '../styles/upload-files-styles.css';

function UploadFiles() {
  const [assetType, setAssetType] = useState('icon');
  const [formData, setFormData] = useState({
    title: '',
    creator: '',
    description: '',
    keywords: '',
    svgCode: '',
    previewImage: null,
    file: null,
  });

  const urls = {
    icon: 'http://localhost:8080/icons/upload',
    vector: 'http://localhost:8080/vectors/upload',
    model: 'http://localhost:8080/models/upload',
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleFileChange = (e) => {
    const { name, files } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: files[0] }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const url = urls[assetType];
    const dataToSend = new FormData();

    if (assetType === 'icon') {
        dataToSend.append('title', formData.title);
        dataToSend.append('creator', formData.creator);
        dataToSend.append('description', formData.description || '');
        dataToSend.append('keywords', formData.keywords);
        dataToSend.append('svgCode', formData.svgCode);
    } else { // vector and 3d models
      if (!formData.previewImage || !formData.file) {
        alert("Please upload both a preview image and the main file.");
        return;
      }
      dataToSend.append('title', formData.title);
      dataToSend.append('creator', formData.creator);
      dataToSend.append('description', formData.description || null);
      dataToSend.append('keywords', formData.keywords);
      dataToSend.append('previewImage', formData.previewImage);
      dataToSend.append('file', formData.file);
    }
    
    try {
      const response = await fetch(url, {
        method: 'POST',
        body: dataToSend,
      });

      if (response.ok) {
        alert('Asset uploaded successfully!');
        setFormData({
          title: '',
          creator: '',
          description: '',
          keywords: '',
          svgCode: '',
          previewImage: null,
          file: null,
        });
        document.querySelector('form').reset();
      } else {
        const errorText = await response.text();
        alert(`Upload failed: ${errorText}`);
      }
    } catch (error) {
      console.error('Network error:', error);
      alert('A network error occurred. Please try again.');
    }
  };
  
  return (
    <div className='file-upload-container'>
      <h2 className='file-upload-heading'>Upload New Asset</h2>

      <div className='file-upload-select-container'>
        <label htmlFor="asset-type" className='file-upload-label'>
          Select Asset Type:
        </label>
        <select
          id="asset-type"
          value={assetType}
          onChange={(e) => setAssetType(e.target.value)}
          className='file-upload-select'
        >
          <option value="icon">Icon</option>
          <option value="vector">Vector</option>
          <option value="model">3D Model</option>
        </select>
      </div>

      <form onSubmit={handleSubmit} className='file-upload-form'>
        <div className='file-upload-form-group'>
          <label htmlFor="title" className='file-upload-label'>
            Title:
          </label>
          <input
            type="text"
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            required
            className='file-upload-input'
          />
        </div>

        <div className='file-upload-form-group'>
          <label htmlFor="creator" className='file-upload-label'>
            Creator:
          </label>
          <input
            type="text"
            id="creator"
            name="creator"
            value={formData.creator}
            onChange={handleChange}
            required
            className='file-upload-input'
          />
        </div>

        <div className='file-upload-form-group'>
              <label htmlFor="keywords" className='file-upload-label'>
                Keywords (comma-separated):
              </label>
              <input
                type="text"
                id="keywords"
                name="keywords"
                value={formData.keywords}
                onChange={handleChange}
                required
                className='file-upload-input'
              />
          </div>
        
        {assetType === 'icon' ? (
          <>
            <div className='file-upload-form-group'>
              <label htmlFor="svgCode" className='file-upload-label'>
                Raw SVG HTML:
              </label>
              <textarea
                id="svgCode"
                name="svgCode"
                value={formData.svgCode}
                onChange={handleChange}
                required
                className='file-upload-textarea'
              />
            </div>
          </>
        ) : (
          <>
            <div className='file-upload-form-group'>
              <label htmlFor="previewImage" className='file-upload-label'>
                Preview Image:
              </label>
              <input
                type="file"
                id="previewImage"
                name="previewImage"
                onChange={handleFileChange}
                required
                className='file-upload-file-input'
              />
            </div>
            <div className='file-upload-form-group'>
              <label htmlFor="file" className='file-upload-label'>
                Asset File:
              </label>
              <input
                type="file"
                id="file"
                name="file"
                onChange={handleFileChange}
                required
                className='file-upload-file-input'
              />
            </div>
          </>
        )}

        <div className='file-upload-form-group'>
          <label htmlFor="description" className='file-upload-label'>
            Description (Optional):
          </label>
          <textarea
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            className='file-upload-textarea'
          />
        </div>

        <button type="submit" className='file-upload-button'>
          Upload
        </button>
      </form>
    </div>
  );
}

export default UploadFiles;