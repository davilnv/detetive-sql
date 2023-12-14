#!/bin/bash

# Caminho para as pastas
LINUX_DIR="linux/detective_sql_jre_linux"
WINDOWS_DIR="windows/detective_sql_jre_windows"

# Nome dos arquivos zip
LINUX_ZIP="linux/detective_sql_jre_linux.zip"
WINDOWS_ZIP="windows/detective_sql_jre_windows.zip"

# Navegar até o diretório linux, compactar a pasta e voltar para o diretório original
cd linux
zip -r detective_sql_jre_linux.zip detective_sql_jre_linux
cd ..

# Navegar até o diretório windows, compactar a pasta e voltar para o diretório original
cd windows
zip -r detective_sql_jre_windows.zip detective_sql_jre_windows
cd ..

# Excluir as pastas
rm -rf $LINUX_DIR
rm -rf $WINDOWS_DIR