call runcrud.bat
if "%ERRORLEVEL%" == "1" goto fail
timeout /t 30
goto openbrowser

:openbrowser
set url="localhost:8080/crud/v1/task/getTasks"
start firefox.exe %url%
if %ERRORLEVEL% == "1" goto fail
goto end

:fail
echo.
echo There were error which you don't like but it is normal

:end
echo.
echo Done