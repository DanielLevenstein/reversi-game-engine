
# 🛠️ Full Build Instructions

### Installing SBT on Windows

If you don't have SBT installed, follow these steps:

1. **Download SBT:**
   - Visit: https://github.com/sbt/sbt/releases/download/v1.10.11/sbt-1.10.11.zip
   - Or go to [SBT GitHub Releases](https://github.com/sbt/sbt/releases) and download the latest version

2. **Extract the ZIP file:**
   - Right-click the downloaded file → **Extract All**
   - Extract to a permanent location (e.g., `C:\sbt` or `C:\Program Files\sbt`)

3. **Add SBT to your PATH:**
   - Press **Win + X** → **System** (or right-click **This PC** → **Properties**)
   - Click **Advanced system settings** (or **System info** → **Advanced system settings**)
   - Click **Environment Variables** button
   - Under **System variables**, click **New**
   - Variable name: `Path`
   - Variable value: `C:\sbt\bin` (adjust path if you extracted to a different location)
   - Click **OK** three times

4. **Verify installation:**
   - Open a new PowerShell window
   - Run:
     ```powershell
     sbt --version
     ```
   - You should see the SBT version output (e.g., `sbt version 1.10.11`)

### Local Development (SBT)

To build and run the project locally:

**On macOS/Linux/PowerShell:**
```bash
sbt compile; sbt run
```

**On Windows Command Prompt:**
```cmd
sbt compile && sbt run
```

The application will be available at `http://localhost:9000`

### Docker Setup (Windows)

#### Step 1: Install WSL 2 (Windows Subsystem for Linux)

WSL 2 is required for Docker Desktop on Windows. Follow these steps:

1. **Open PowerShell as Administrator** (right-click → Run as Administrator)

2. **Check WSL status:**
   ```powershell
   wsl --status
   ```

3. **If WSL is not installed, install it:**
   ```powershell
   wsl --install -d Ubuntu
   ```
   This will:
   - Install WSL 2
   - Download and install Ubuntu
   - Prompt you to create a Linux username and password

4. **Update WSL kernel** (if needed):
   ```powershell
   wsl --update --web-download
   ```

5. **Verify installation:**
   ```powershell
   wsl -l -v
   ```
   You should see Ubuntu listed with Version 2.

#### Step 2: Download and Install Docker Desktop

1. Download [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/)
2. Run the installer and follow the setup wizard
3. Ensure **"WSL 2 based engine"** is selected during installation
4. Restart your computer when prompted
5. Launch Docker Desktop from the Start Menu
6. Wait 2-3 minutes for Docker to fully initialize

#### Step 3: Verify Docker Installation

```powershell
docker --version
docker ps
```

### Building and Running with Docker

#### Build Docker Image

```bash
docker build -t reversi-game-engine:latest .
```

#### Run Container Locally

```bash
docker run -p 9000:9000 reversi-game-engine:latest
```

The application will be available at `http://localhost:9000`

#### Check Container Logs

```bash
docker logs <container-id>
```

#### Stop Container

```bash
docker stop <container-id>
```

### 🔧 Docker Troubleshooting

#### Issue: "Docker Desktop is unable to start"

**Solution:**

1. Verify WSL distributions are installed:
   ```powershell
   wsl -l -v
   ```
   If no distributions are listed, install Ubuntu:
   ```powershell
   wsl --install -d Ubuntu
   ```

2. Check Docker service status:
   ```powershell
   Get-Service -Name "com.docker.service" | Select-Object Status
   ```

3. If service is stopped, restart Docker Desktop:
   - Close Docker Desktop completely
   - Wait 10 seconds
   - Reopen Docker Desktop from Start Menu
   - Wait 3-5 minutes for it to fully initialize

4. Verify Docker is responsive:
   ```powershell
   docker ps
   ```

#### Issue: "Error response from daemon" or "500 Internal Server Error"

**Solution:**

1. Restart Docker service:
   ```powershell
   & "C:\Program Files\Docker\Docker\Docker Desktop.exe"
   ```
   Wait 2-3 minutes before proceeding.

2. If still failing, restart your computer and launch Docker Desktop again.

#### Issue: "Windows Subsystem for Linux has no installed distributions"

**Solution:**

1. Open PowerShell as Administrator
2. Install Ubuntu:
   ```powershell
   wsl --install -d Ubuntu
   ```
3. Follow the prompts to create a Unix user and password
4. Verify installation:
   ```powershell
   wsl -l -v
   ```

#### Issue: "The command 'docker' could not be found in this WSL 2 distro"

**Solution:**

This is normal - Docker runs on Windows/Docker Desktop, not in WSL. The error appears when running `docker` commands inside WSL. Instead:

1. Use PowerShell/Command Prompt (Windows side) for `docker` commands
2. Or enable Docker integration in Docker Desktop settings:
   - Open Docker Desktop → Settings → Resources → WSL Integration
   - Enable integration for Ubuntu
   - Click "Apply & Restart"

#### Issue: "docker build" fails with various errors

**Checklist:**

- [ ] Docker Desktop is running (`docker ps` returns no error)
- [ ] WSL 2 Ubuntu is installed (`wsl -l -v` shows Ubuntu Version 2)
- [ ] You have adequate disk space (at least 10GB free)
- [ ] Internet connection is stable
- [ ] You're in the project root directory with the Dockerfile

**Try these commands in order:**

```powershell
# Restart Docker
docker system prune -a --volumes

# Retry build
docker build -t reversi-game-engine:latest .
```

If that fails, provide the full error output for more specific troubleshooting.

---
