@echo off
rem Joel Korhonen 2018-May-15
rem Change EDITION to match with the Wiktionary edition you have downloaded
rem 2018-10-03 Updated edition
set EDITION=20181001

rem This param is currently unused
rem set LANGNAME=English

set LANG=ALL
rem set LANGCODE=en
set LANGCODE=ALL
set METADATAENGLISH=true
rem Only languages supplied in a language file are to be processed
set ONLYLANGS=false

echo "Starting after this pause"
pause

rem Remove this normally
rem goto mainloop

cd /D %WIKT%\Scripts
call "ReadStripped SD ALL.cmd" %EDITION% %LANG% %LANGCODE% %METADATAENGLISH% %ONLYLANGS%

:mainloop
cd /D %WIKT%\Scripts
if not exist ..\continfo.txt goto ending
for /F %%i in (..\continfo.txt) do @set RESTARTATLINE=%%i
call "ReadStripped SD ALL restart.cmd" %EDITION% %RESTARTATLINE% %LANG% %LANGCODE% %METADATAENGLISH% %ONLYLANGS%
goto mainloop

:ending