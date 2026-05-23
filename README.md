# OpenAssets



https://github.com/user-attachments/assets/42a8fef0-c970-4b72-8c80-ebb013c2f7a9



## Overview
OpenAssets is a platform designed to provide completely free and unrestricted access to graphic assets like icons, vectors, and 3D models. Born out of the real-world frustrations of navigating paywalls, annoying ads, and download limits in daily design workflows, this application offers a community-backed alternative where creators can freely find, customize, and share resources. This project was built from the ground up to solidify my full-stack web development skills.

## Key Features
- **Dynamic Search & Browsing:** A responsive interface that allows users to seamlessly search the database for specific assets without full page reloads.

- **On-the-Fly Customization:** Interactive UI components that allow users to adjust SVG sizes and colors directly within the browser before downloading.

- **Community Uploads:** A secure pipeline for users to upload their own assets (including preview images and metadata) to give back to the community.

- **Optimized File Storage:** A dedicated server-side file system designed to handle heavy graphic files efficiently, keeping the primary MySQL database lightweight and fast.

## Tech Stack & Architecture
- **Frontend:** React, React Router, Vanilla CSS (Grid/Flexbox)

- **Backend:** Java Spring Boot, RESTful APIs

- **Database:** MySQL

- **Infrastructure:** Docker, Nginx



## Getting Started

Follow the steps below to run the project locally using Docker.

### Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) must be installed and running.

### Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/mo7amedgisimelsied/OpenAssets

2. **Navigate to the project root folder**
   ```bash
   cd your-repo/OpenAssets

3. **Start the services with Docker Compose**
   ```bash
   docker-compose up
  This will build and start the containers defined in the docker-compose.yml file.

3. **Access the application**

  Once the containers are running, you should see the message:
   ```bash
   Spring Boot is running...
Open your browser and go to http://localhost to access the application.

   
