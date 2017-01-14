#!/bin/bash

TMPL_PACKAGE="eu.livotov.labs.webskel"
TMPL_PACKAGE_TLD="eu"
TMPL_NAME="WebApplicationSkeleton"

echo "Livotov Labs > Server Project Template v1.0"
echo

read -p "Project name: " PROJECT_NAME
read -p "Java package name: " PROJECT_PACKAGE
read -p "Project location [$1]: " PROJECT_LOCATION

PROJECT_LOCATION=${PROJECT_LOCATION:-$1}

if [ -z "$PROJECT_LOCATION" ]; then
    echo "Project location must be specified in order to continue !"
   exit
fi

mkdir -p "$PROJECT_LOCATION"
cp -r template/* "$PROJECT_LOCATION"

find "$PROJECT_LOCATION/" -type f -name '*.gradle' -exec sed -i '' "s/$TMPL_NAME/$PROJECT_NAME/g" {} \;
find "$PROJECT_LOCATION/" -type f -name '*.java' -exec sed -i '' "s/$TMPL_PACKAGE./$PROJECT_PACKAGE./g" {} \;
find "$PROJECT_LOCATION/" -type f -name '*.xml' -exec sed -i '' "s/$TMPL_PACKAGE./$PROJECT_PACKAGE./g" {} \;
find "$PROJECT_LOCATION/" -type f -name '*.gradle' -exec sed -i '' "s/$TMPL_PACKAGE./$PROJECT_PACKAGE./g" {} \;
find "$PROJECT_LOCATION/" -type f -name '*.properties' -exec sed -i '' "s/$TMPL_PACKAGE./$PROJECT_PACKAGE./g" {} \;


PATH_PKG_PART="${PROJECT_PACKAGE//.//}"
PATH_TPKG_PART="${TMPL_PACKAGE//.//}"

mkdir -p "$PROJECT_LOCATION/api/src/main/java/$PATH_PKG_PART"
cp -r "$PROJECT_LOCATION/api/src/main/java/$PATH_TPKG_PART/" "$PROJECT_LOCATION/api/src/main/java/$PATH_PKG_PART/"
rm -rf "$PROJECT_LOCATION/api/src/main/java/$TMPL_PACKAGE_TLD"

mkdir -p "$PROJECT_LOCATION/front/src/main/java/$PATH_PKG_PART"
cp -r "$PROJECT_LOCATION/front/src/main/java/$PATH_TPKG_PART/" "$PROJECT_LOCATION/front/src/main/java/$PATH_PKG_PART/"
rm -rf "$PROJECT_LOCATION/front/src/main/java/$TMPL_PACKAGE_TLD"

mkdir -p "$PROJECT_LOCATION/server/src/main/java/$PATH_PKG_PART"
cp -r "$PROJECT_LOCATION/server/src/main/java/$PATH_TPKG_PART/" "$PROJECT_LOCATION/server/src/main/java/$PATH_PKG_PART/"
rm -rf "$PROJECT_LOCATION/server/src/main/java/$TMPL_PACKAGE_TLD"

mkdir -p "$PROJECT_LOCATION/server/src/main/resources/$PATH_PKG_PART"
cp -r "$PROJECT_LOCATION/server/src/main/resources/$PATH_TPKG_PART/" "$PROJECT_LOCATION/server/src/main/resources/$PATH_PKG_PART/"
rm -rf "$PROJECT_LOCATION/server/src/main/resources/$TMPL_PACKAGE_TLD"

rm -rf "$PROJECT_LOCATION/build"
rm -rf "$PROJECT_LOCATION/api/build"
rm -rf "$PROJECT_LOCATION/server/build"
rm -rf "$PROJECT_LOCATION/docs/build"
rm -rf "$PROJECT_LOCATION/front/build"
rm -rf "$PROJECT_LOCATION/.gradle"
