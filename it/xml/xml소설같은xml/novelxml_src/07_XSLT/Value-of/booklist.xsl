<?xml version="1.0" encoding="euc-kr"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />

	<xsl:template match="/">
		<p>Name : <xsl:value-of select="book/name" /></p>
		<p>Page : <xsl:value-of select="book/page" /></p>
		<p>ISBN : <xsl:value-of select="book/@isbn" /></p>
	</xsl:template>
</xsl:stylesheet>
<!-- //
Name : Programming .NET Components, Second Edition 
Page : 648
ISBN : h0-596-00762-0
// -->