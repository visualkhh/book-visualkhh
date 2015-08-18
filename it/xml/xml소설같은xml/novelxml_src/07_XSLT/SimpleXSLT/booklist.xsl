<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<HTML>
				<HEAD>
						<TITLE>Book Catalog</TITLE>
				</HEAD>
				<BODY>
						<xsl:apply-templates select="book" />
				</BODY>
		</HTML>
	</xsl:template>
	
	<xsl:template match="book">
		<xsl:value-of select="name" />
	</xsl:template>
</xsl:stylesheet>
<!-- //
Programming .NET Components, Second Edition 
// -->