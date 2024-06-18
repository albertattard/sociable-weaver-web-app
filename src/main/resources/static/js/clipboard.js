async function writeElementByIdToClipboard(id) {
    const html = document.getElementById(id).innerHTML;

    const clipboardItem = new ClipboardItem({
      'text/plain': new Blob([html], { type: 'text/plain' })
    });

    try {
      await navigator.clipboard.write([clipboardItem]);
      console.log("Text copied to clipboard");
    } catch (error) {
      console.error(error.message);
    }
}
