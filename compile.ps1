# Step 1: Delete all .class files in the current directory
Get-ChildItem -Path . -Filter *.class -Recurse | Remove-Item -Force -ErrorAction SilentlyContinue

# Step 2: Set filename
$javaFile = "Solution"

# Check if Solution.java exists
if (-Not (Test-Path "$javaFile.java")) {
    Write-Host "Error: File '$javaFile.java' not found in current directory." -ForegroundColor Red
    exit 1
}

# Step 3: Compile Solution.java
javac "$javaFile.java"

# Check if compilation succeeded
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit $LASTEXITCODE
}

# Step 4: Run the compiled Java class
java $javaFile
