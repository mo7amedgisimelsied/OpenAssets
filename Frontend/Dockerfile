# Stage 1: Build the React application
# Uses a Node.js image to install dependencies and build the React app.
FROM node:20-alpine AS build

# Set the working directory inside the container
WORKDIR /app/frontend

# Copy package.json and package-lock.json to install dependencies
# This is done separately to leverage Docker's caching, so dependencies aren't re-installed
# if only source code changes.
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the frontend source code
COPY . .

# Build the React application for production
# The 'build' script is typically defined in package.json
RUN npm run build

# Stage 2: Serve the application with Nginx
# Uses a lightweight Nginx image to serve the static files produced by the build stage.
FROM nginx:alpine

# Copy the Nginx default configuration.
# We'll create a custom Nginx config to serve our React app.
COPY --from=build /app/frontend/nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built React application files from the build stage to the Nginx public directory
COPY --from=build /app/frontend/dist /usr/share/nginx/html

# Expose port 80, which Nginx will listen on for incoming HTTP requests
EXPOSE 80

# Command to start Nginx when the container launches
CMD ["nginx", "-g", "daemon off;"]