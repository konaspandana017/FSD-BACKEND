Remove-Item -Recurse -Force .git
git init
git add .
git commit -m "Initial commit: Premium Career Platform Architecture & Resources Module"
git branch -M main
git remote add origin https://github.com/konaspandana017/FSD-BACKEND.git
git push -u origin main -f
