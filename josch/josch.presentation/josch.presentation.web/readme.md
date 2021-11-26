# Josch.web frontend

## Icons

Icons are used in order to guide the user intuitively without relying upon text. They should enhance the website's
content and should not be used for decoration purpose. We differ between two kinds of icons, the one's supported by the
FontAwesome Framework and custom icons.
---

### FontAwesome

FontAwesome is the web's most popular icon set and toolkit. In order to include any font awesome icon we can use the
following basic code in any HTML element within:

```html
<i class="fa fa-arrow-circle-right"></i>
```

In this snippet we see two css classes. The `fa` class is used to indicate we are using an icon and the `fa-*` is used
to determine the actual icon, where * is the shape. The shapes can be taken from
the [FontAwesome Cheatsheet](https://fontawesome.com/cheatsheet?from=io).

---

### Custom Icons

Custom icons are taken from the local application primarily. They are used when there is no fitting FontAwesome icon
available as the process of embedding them is slightly more complex. The style of these buttons is defined
within `src/main/resources/static/css/icons.css`.

Such a button has to have the following example structure:

```html
<button id="contain-btn" class="btn-ico">
    <svg id="contain-svg" class="svg-reg" viewBox="0 0 2000 2000">
        <path id="contain-pth" class="pc-primary pi-is-eqv"/>
    </svg>
</button>
```

The concrete elements of this example as well as naming convention and dev notes are explained in the following
paragraphs.

#### Buttons

The `<button />` element should use the`btn-ico` class. It strips the button of any button specific style.

#### SVGs

The `<svg />` element has *two* important attributes:

- The `viewBox="0 0 2000 2000"` attribute defines the coordinate system, i.e. the canvas to draw on. It *has to* be
  these specific values, because the paths defined in `icons.css` were drawn on 2000px². This has *no* effect on the
  actual size. but rather the more pixels the smoother the shape will look.

- The `svg-reg` class is used for the *actual* size of the icon. It can be replaced by any of the
  `svg-*` size classes.

#### Paths

The `<path />` elements define actual shapes. It has also *two* important classes:

- `pc-primary` defines the color of the shape. These comply with the most common bootstrap colors. These colors are
  defined in `src/main/resources/static/css/bootstrap.css`. It can be replaced by any of the color classes `pc-*`.

- `pi-is-eqv` defines the actual shape. It can be replaced with any of the shape classes
  `pi-*`.

#### Naming Conventions

The `id` of elements belonging together should represent this. By convention these element ids are named by the
following pattern: `purpose-element`, where:

- The purpose describes the intention of the element as short as possible but as long as necessary. For example
  does `id="contain-*"` indicate the purpose to display the semantic containment relation.

- the element describes the actual html element sans vowels.

#### Dev Notes

While it would be possible to implement new custom html elements to simplify the process, we do not use it on purpose,
because these are only supported by the Chrome and Opera browsers (as of February 2021) or it would require more 
resources to develop than available. 
---