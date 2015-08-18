<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                      version="1.0">
	<xsl:output method="html" />
    
	<xsl:param name="paramInt" />

	<xsl:template match="/">
		<HTML>
			<HEAD>
				<TITLE>Book Catalog</TITLE>
			</HEAD>
			<BODY>
				<h2><xsl:value-of select="$paramInt" /></h2>
			</BODY>
		</HTML>
	</xsl:template>
</xsl:stylesheet>