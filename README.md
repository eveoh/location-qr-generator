Location QR Generator
=====================

This QR generator consumes an Excel spreadsheet with location hostkeys and outputs a QR code image (PNG) which points to the timetable of the location in the mobile MyTimetable interface.

Usage
-----

The application is used from the command line:
```
> generate.bat <input file> <output directory>
```

Settings
--------

Application settings can be overriden in `application.properties` in the working directory.

<dl>
<dt>Qr.Width
<dd>Preferred width of the output images (default is `1000`).

<dt>Qr.Height
<dd>Preferred height of the output images (default is `1000`).

<dt>Url.Scheme
<dd>Scheme of the MyTimetable base URL (default is `https`)

<dt>Url.Host
<dd>Hostname of the MyTimetable base URL (no default).

<dt>Url.TimetableType
<dd>Timetable type name (default is `location`).

<dt>Excel.HostKeyColumnHeader
<dd>Text to look for as HostKey column header in the spreadsheet (default is `HostKey`).
</dd>

License
-------

    Copyright 2013 - 2014 Eveoh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
